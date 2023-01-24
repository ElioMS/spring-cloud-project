package pe.chwa.delivery.model.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.chwa.delivery.model.entity.Header;

public interface HeaderRepository extends JpaRepository<Header, Long> {
    Header findBySerial(@Param("serial") String serial);

    @Transactional
    @Modifying
    @Query("UPDATE Header h SET h.serial = :serial")
    void updateSerial(@Param("serial") String serial);
}
