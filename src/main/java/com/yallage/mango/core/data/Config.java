package com.yallage.mango.core.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Config {
    List<Database> databases;
}
