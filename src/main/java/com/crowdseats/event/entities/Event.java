package com.crowdseats.event.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "events", schema = "event_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_id")
    private String eventId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private int totalSeats;
    
    @Column
    private String category;

    @Column
    private String organizer;

    @Column
    private Date EventStartDate;

    @Column
    private Date EventEndDate;

    @CreationTimestamp
    @Column
    private Date createdOn;

    @UpdateTimestamp
    @Column
    private Date lastUpdated;
}
