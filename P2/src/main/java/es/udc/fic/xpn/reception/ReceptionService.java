package es.udc.fic.xpn.reception;

import java.util.Optional;

public interface ReceptionService {

    public Integer create(ReceptionDto dto);

    public Optional<Reception> findById(Integer id);
}
