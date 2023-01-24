package pe.chwa.delivery.http.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.chwa.delivery.http.client.ProductClient;
import pe.chwa.delivery.http.request.DeliveryRequest;
import pe.chwa.delivery.model.dto.Product;
import pe.chwa.delivery.model.dto.ProductPatch;
import pe.chwa.delivery.model.entity.Header;
import pe.chwa.delivery.model.entity.Order;
import pe.chwa.delivery.model.enums.ActionsEnum;
import pe.chwa.delivery.model.repository.HeaderRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final HeaderRepository headerRepository;
    private final ProductClient productClient;

    public DeliveryServiceImpl(HeaderRepository headerRepository,
                               ProductClient productClient) {
        this.headerRepository = headerRepository;
        this.productClient = productClient;
    }

    @Override
    public Header findAll() {
        return null;
    }

    @Override
    public Header findDeliveryOrderById(Long id) {
        Optional<Header> optHeader = headerRepository.findById(id);

        if (optHeader.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "The delivery order doesn't exists");
        }

        Header header = optHeader.get();

        List<Order> orders = header.getOrders().stream().map(order -> {
            Product product = productClient.getProduct(order.getProductId());
            order.setProduct(product);
            return order;
        }).toList();

        header.setOrders(orders);

        return header;
    }

    @Override
    public Header createDeliveryOrder(DeliveryRequest deliveryRequest) {
        Header header = headerRepository.save(createHeaderBuilder(deliveryRequest));
        String serialNumber = createSerialNumber(deliveryRequest.getCustomerName(), header.getId());
        headerRepository.updateSerial(serialNumber);
        header.setSerial(serialNumber);

        deliveryRequest.getOrders().forEach(item -> {
            ProductPatch patch = new ProductPatch();
            patch.setAction(ActionsEnum.UPDATE_STOCK);
            patch.setKey("stock");
            double stockValue = item.getQuantity() * -1;
            patch.setValue(Double.toString(stockValue));

            productClient.updateProductValues(item.getProductId(), patch);
        });

        return header;
    }

    protected Header createHeaderBuilder(DeliveryRequest deliveryRequest) {
        return Header.builder()
              .customerName(deliveryRequest.getCustomerName())
              .orders(deliveryRequest.getOrders()).build();
    }

    protected String createSerialNumber(String customerName, Long headerId) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
        int year = cal.get(Calendar.YEAR);
        String[] customerSplit = customerName.split(" ");
        String name = Arrays.stream(customerSplit)
                .map((c) -> c.substring(0, 1))
                .collect(Collectors.joining())
                .toUpperCase();
        return ""+year+name+"-"+headerId;
    }
}
