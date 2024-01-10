package com.auto.companion.domain.exception;

import lombok.Builder;

@Builder
public record RestException(String errorMessage) {

}
