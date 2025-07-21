package br.com.opinai.api.gestao.produto.domain.product.ref;

public record PhotosRef(
        String url,
        String alText,
        boolean isMain,
        int sortOrder
) {

    public static PhotosRef with( String url,String alText, boolean isMain, int sortOrder) {
        return new PhotosRef(url, alText, isMain, sortOrder);
    }

}
