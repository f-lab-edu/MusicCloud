package com.flab.livecommerce.infrastructure.generator;

import com.flab.livecommerce.domain.user.TokenGenerator;
import java.util.UUID;

public class RandomTokenGenerator implements TokenGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
