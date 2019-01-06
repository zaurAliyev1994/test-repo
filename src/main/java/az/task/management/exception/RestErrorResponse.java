package az.task.management.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Error Rest response
 */
@ApiModel("Error response from Rest service")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestErrorResponse {

    @ApiModelProperty("Error code - NOT_FOUND, INTERNAL_SERVER_ERROR etc")
    private String code;
    @ApiModelProperty("HTTP error code - 404, 503 etc")
    private int httpCode;
    @ApiModelProperty("Is error flag")
    private boolean isError = true;
    @ApiModelProperty("Error message")
    private String message;

    public RestErrorResponse(HttpStatus status, String message) {
        this.code = status.name();
        this.httpCode = status.value();
        this.message = message;
    }
}