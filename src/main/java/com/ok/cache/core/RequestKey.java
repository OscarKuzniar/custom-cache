package com.ok.cache.core;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RequestKey {

    String parameter;

    String parameterValues;
}
