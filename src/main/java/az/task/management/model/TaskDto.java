package az.task.management.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel("task dto")
public class TaskDto {
    @ApiModelProperty("task identifier")
    private Long id;

    @ApiModelProperty("task name")
    private String name;

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

    public TaskDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
