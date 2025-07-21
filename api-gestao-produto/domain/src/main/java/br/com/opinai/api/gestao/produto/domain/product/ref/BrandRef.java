package br.com.opinai.api.gestao.produto.domain.product.ref;

public record BrandRef(String id, String name) {

    public static BrandRef with(String id, String name) {
        return  new BrandRef(id, name);
    }
}
