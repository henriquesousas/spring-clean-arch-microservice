package com.jobee.admin.service.domain.genre;


import com.jobee.admin.service.domain.Identifier;
import com.opinai.shared.domain.utils.IdUtils;

public class GenreId extends Identifier {

    private GenreId(String value) {
        super(value);
    }

    public static GenreId unique() {
        return new GenreId(IdUtils.uuid());
    }

    public static GenreId from(String value) {
        return new GenreId(value);
    }

}
