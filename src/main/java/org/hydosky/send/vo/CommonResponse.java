package org.hydosky.send.vo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <h3>hydosky-send</h3>
 * <p>
 *     统计数据通用响应体
 * </p>
 * Created by yang on 19-12-19 上午9:51
 * updated by yang on 19-12-19 上午9:51
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class CommonResponse {

    @NonNull
    private String x;

    @NonNull
    private Long y;

    private Integer s;
}
