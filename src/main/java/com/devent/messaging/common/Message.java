package com.devent.messaging.common;

import java.util.Map;
import java.util.Optional;

/**
 * @author wuk
 * @description:
 * @menu
 * @date 2023/7/21 14:25
 */
public interface Message {
    String getId();
    Map<String, String> getHeaders();
    String getPayload();

    String ID = "ID";
    String PARTITION_ID = "PARTITION_ID";
    String DESTINATION = "DESTINATION";
    String DATE = "DATE";

    Optional<String> getHeader(String name);
    String getRequiredHeader(String name);

}
