package iuh.edu.vn.backend.enums;

public enum SkillLevel {
    MASTER(1),
    BEGINNER(2),
    ADVANCED(3),
    PROFESSIONAL(4),
    INTERMEDIATE(5);

    private final int value;

    SkillLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
