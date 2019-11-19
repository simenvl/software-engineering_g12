package gruppe12.Model;

import java.time.LocalDate;

public class User extends Person implements Comparable<User>{

    private int rankNumber;
    private String rank;
    private int participantNumber;
    private static int id = 220000;

    public User(String name, LocalDate birthDate, String email, int phone){
        super(name, id++, birthDate, email, phone);
    }

    public User(Person person, int participantNumber){
        super(person.getName(), person.getId(), person.getBirthDate(), person.getEmail(), person.getPhone());
        this.participantNumber = participantNumber;
    }

    public User(String name,int id, LocalDate birthDate, String email, int phone) {
        super(name, id, birthDate, email, phone);
    }

    public int getParticipantNumber() {
        return participantNumber;
    }

    public void setParticipantNumber(int participantNumber) {
        this.participantNumber = participantNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(int rankNumber) {
        this.rankNumber = rankNumber;
    }

    @Override
    public int compareTo(User compareUser) {
        int compareRank = compareUser.rankNumber;
        return this.rankNumber - compareRank;
    }
}
