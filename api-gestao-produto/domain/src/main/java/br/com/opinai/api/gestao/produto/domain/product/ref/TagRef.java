package br.com.opinai.api.gestao.produto.domain.product.ref;

public record TagRef(String id, String name) {

    public static TagRef with(String id, String name) {
        return new TagRef(id, name);
    }
}
