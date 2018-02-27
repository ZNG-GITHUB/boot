package com.zng.system.job.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by John.Zhang on 2018/2/26.
 */
public class JobDto {
    private String jobClassName;
    private String jobGroupName;
    private List<TriggerDTO> triggers;

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public static class TriggerDTO {
        private String triggerState;
        private Integer priority;
        private String description;
        private String previousFireTime;
        private String nextFireTime;
        private String cronExpression;
        private String triggerName;
        private String triggerGroupName;

        public String getTriggerState() {
            return triggerState;
        }

        public void setTriggerState(String triggerState) {
            this.triggerState = triggerState;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPreviousFireTime() {
            return previousFireTime;
        }

        public void setPreviousFireTime(String previousFireTime) {
            this.previousFireTime = previousFireTime;
        }

        public String getNextFireTime() {
            return nextFireTime;
        }

        public void setNextFireTime(String nextFireTime) {
            this.nextFireTime = nextFireTime;
        }
        public String getCronExpression() {
            return cronExpression;
        }

        public void setCronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
        }

        public String getTriggerName() {
            return triggerName;
        }

        public void setTriggerName(String triggerName) {
            this.triggerName = triggerName;
        }

        public String getTriggerGroupName() {
            return triggerGroupName;
        }

        public void setTriggerGroupName(String triggerGroupName) {
            this.triggerGroupName = triggerGroupName;
        }
    }

    public List<TriggerDTO> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<TriggerDTO> triggers) {
        this.triggers = triggers;
    }
}
