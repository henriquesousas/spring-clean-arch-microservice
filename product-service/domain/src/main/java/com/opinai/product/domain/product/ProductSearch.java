package com.opinai.product.domain.product;


public record ProductSearch(
        String categoryId,
        int page,       // Página corrente
        int perPage,   // Numero de item por página
        String terms, // filtro que vai ser pesquisa
        String sort,  // Propriedade que sera ordenada
        String directions
) {

    public static ProductSearch from(String categoryId,
                                int page,
                                int perPage,
                                String terms,
                                String sort,
                                String directions) {
        return  new ProductSearch(categoryId, page, perPage, terms, sort, directions);
    }
}
