package es.udc.fic.xpn.warehouse;

public class WarehouseMapper {

    public static WarehouseDto entityToDto(Warehouse warehouse) {
        WarehouseDto warehouseDto = new WarehouseDto();

        warehouseDto.setId(warehouse.getId());
        warehouseDto.setName(warehouse.getName());
        warehouseDto.setLocation(warehouse.getLocation());

        return warehouseDto;
    }

    public static Warehouse dtoToEntity(WarehouseDto warehouseDto) {
        Warehouse warehouse = new Warehouse();

        warehouse.setId(warehouseDto.getId());
        warehouse.setName(warehouseDto.getName());
        warehouse.setLocation(warehouseDto.getLocation());

        return warehouse;
    }
}
