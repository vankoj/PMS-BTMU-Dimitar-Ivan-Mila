
Create Table Accounts
(
    Username VARCHAR(20) NOT NULL PRIMARY KEY,
    Email VARCHAR(100) NOT NULL ,
    UserPasswordHash VARCHAR(100) NOT NULL,
    UserPasswordSalt VARCHAR(100) NOT NULL
);

CREATE TABLE GroupChat
(
    IDGroup INT PRIMARY KEY,
    GroupName VARCHAR(50)
);

Create Table Recievers
(
    GroupId INT,
    UserAccountReciever VARCHAR(20),
    Constraint User_Reciever FOREIGN KEY(UserAccountReciever)
        REFERENCES Accounts(Username)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    Constraint User_Group FOREIGN KEY(GroupId)
        REFERENCES GroupChat(IDGroup)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

Create Table Messages
(
    IDMessage SERIAL NOT NULL PRIMARY KEY,
    UserAccountSender VARCHAR(20),
    IDGroups INT,
    Messages VARCHAR(255),
    DateSent TIMESTAMP,
    Constraint User_Sender FOREIGN KEY(UserAccountSender)
        REFERENCES Accounts(Username)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    Constraint User_Recievers FOREIGN KEY(IDGroups)
        REFERENCES GroupChat(IDGroup)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
Create Table Friendship
(
    UserRequest varchar(20),
    UserRecieve varchar(20),
    Status int,
    CONSTRAINT Friendship_Status FOREIGN KEY(Status)
        REFERENCES Status(ID)

);
Create Table Status
(
    ID SERIAL not null primary key,
    Name_ varchar(50)
);
