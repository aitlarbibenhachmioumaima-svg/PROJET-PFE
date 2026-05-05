package com.ocp.production_epierrage.repository;



import com.ocp.production_epierrage.entity.KPI;
import com.ocp.production_epierrage.entity.KPIDetail;
import com.ocp.production_epierrage.entity.KPIType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface KPIDetailRepository extends JpaRepository<KPIDetail, Long> {
    List<KPIDetail> findByKpi(KPI kpi);
    Optional<KPIDetail> findByKpiAndType(KPI kpi, KPIType type);

    @Query("SELECT d.type.code, AVG(d.valeur) FROM KPIDetail d " +
            "WHERE d.kpi.id IN :kpiIds GROUP BY d.type.code")
    List<Object[]> avgByTypeForKpis(@Param("kpiIds") List<Long> kpiIds);
}