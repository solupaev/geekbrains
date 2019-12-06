package erth.task_tracker.enums;

public enum Status {
    OPEN("Открыта"),
    PLANED("Запланирована"),
    INPROGRESS("В работе"),
    CLOSED("Закрыта");

    private String rus;

    public String getRus() {
        return rus;
    }

    Status(String rus) {
        this.rus = rus;
    }

    public boolean isSelected(Status selectedStatus){
        if (selectedStatus != null) {
            return selectedStatus.equals(this);
        }
        return false;
    }
}