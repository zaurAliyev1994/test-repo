package az.task.management.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("task dto")
public class TaskDto {

    @ApiModelProperty("task identifier")
    private Long id;

    @NotEmpty(message="name is empty")
    @ApiModelProperty("task name")
    private String name;

    @NotEmpty(message="description is empty")
    @ApiModelProperty("task description")
    private String description;

    @ApiModelProperty("task creation date")
    private LocalDateTime createDate;

    @ApiModelProperty("task update date")
    private LocalDateTime updateDate;

    @ApiModelProperty("is done")
    private boolean isDone;

    @ApiModelProperty("task status")
    private String status;
}
