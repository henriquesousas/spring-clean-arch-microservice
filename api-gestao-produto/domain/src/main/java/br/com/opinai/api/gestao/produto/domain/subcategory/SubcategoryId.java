package br.com.opinai.api.gestao.produto.domain.subcategory;


import com.opinai.shared.domain.Identifier;
import com.opinai.shared.domain.utils.IdUtils;

public class SubcategoryId extends Identifier {

    private SubcategoryId(String value) {
        super(value);
    }

    public static SubcategoryId unique() {
        return new SubcategoryId(IdUtils.uuid());
    }

    public static SubcategoryId from(String value) {
        return new SubcategoryId(value);
    }

}
