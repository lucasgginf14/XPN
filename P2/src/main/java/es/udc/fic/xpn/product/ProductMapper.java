package es.udc.fic.xpn.product;

public class ProductMapper {

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setSku(product.getSku());
        productDto.setName(product.getName());
        productDto.setType(product.getType());

        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();

        product.setSku(productDto.getSku());
        product.setName(productDto.getName());
        product.setType(productDto.getType());

        return product;
    }
}
