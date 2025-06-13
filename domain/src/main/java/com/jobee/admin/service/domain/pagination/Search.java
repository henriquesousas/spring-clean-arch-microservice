package com.jobee.admin.service.domain.commons.pagination;

public record Search(
        int page,       // Página corrente
        int perPage,   // Numero de item por página
        String terms, // filtro que vai ser pesquisa
        String sort,  // Propriedade que sera ordenada
        String directions  // Asc ou Desc
) {
}
