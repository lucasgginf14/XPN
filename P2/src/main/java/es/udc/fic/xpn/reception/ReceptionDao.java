package es.udc.fic.xpn.reception;

import java.util.Optional;

public interface ReceptionDao {

    Integer create(Reception reception);

    Optional<Reception> findById(Integer id);
}
