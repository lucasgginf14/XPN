package es.udc.fic.xpn.reception;

public class ReceptionMapper {

    public static ReceptionDto entityToDto(Reception r) {
        ReceptionDto dto = new ReceptionDto();
        dto.setId(r.getId());
        dto.setDate(r.getDate());
        dto.setQuantity(r.getQuantity());
        dto.setWarehouseId(r.getWarehouseId());
        dto.setProductSku(r.getProductSku());
        dto.setSupplier(r.getSupplier());
        return dto;
    }

    public static Reception dtoToEntity(ReceptionDto dto) {
        return new Reception(
                dto.getId(),
                dto.getQuantity(),
                dto.getSupplier(),
                dto.getDate(),
                dto.getWarehouseId(),
                dto.getProductSku()
        );
    }
}
