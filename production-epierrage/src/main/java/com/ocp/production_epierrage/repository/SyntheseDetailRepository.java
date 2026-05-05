package com.ocp.production_epierrage.repository;



import com.ocp.production_epierrage.entity.IndicateurSynthese;
import com.ocp.production_epierrage.entity.SyntheseDetail;
import com.ocp.production_epierrage.entity.SyntheseProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SyntheseDetailRepository extends JpaRepository<SyntheseDetail, Long> {

    List<SyntheseDetail> findBySynthese(SyntheseProduction synthese);
    Optional<SyntheseDetail> findBySyntheseAndPosteAndIndicateur(
            SyntheseProduction s, Integer poste, IndicateurSynthese indicateur);

    @Query("SELECT d.poste, d.indicateur.code, AVG(d.valeur) FROM SyntheseDetail d " +
            "WHERE d.synthese.id IN :ids GROUP BY d.poste, d.indicateur.code")
    List<Object[]> avgByPosteAndIndicateur(@Param("ids") List<Long> syntheseIds);
}