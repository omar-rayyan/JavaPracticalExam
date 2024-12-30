package com.axsosacademy.teams.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "teams")
public class Team {
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Team name is required.")
    @Size(min = 3, max = 30, message = "Team name must be between 3 and 30 characters.")
    private String name;
    
    @NotEmpty(message = "Game Day is required.")
    @Size(min = 1, max = 30, message = "Game Day is required.")
    private String gameDay;
    
    @NotNull(message = "Cannot be null")
    @Min(value = 1, message = "Skill level must be greater than 1")
    @Max(value = 5, message = "Skill level must be 5 or less")
    private int skill;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User creator;
    
    private ArrayList<String> players = new ArrayList<String>();
    
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
    
    public Team() {}
    
    public Team(String name, String gameDay, int skill) {
        this.name = name;
        this.gameDay = gameDay;
        this.skill = skill;
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

	public String getGameDay() {
		return gameDay;
	}

	public void setGameDay(String gameDay) {
		this.gameDay = gameDay;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}
}