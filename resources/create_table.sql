
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
    GroupName INT,
    UserAccountReciever VARCHAR(20),
    Constraint User_Reciever FOREIGN KEY(UserAccountReciever)
        REFERENCES Accounts(Username)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    Constraint User_Group FOREIGN KEY(GroupName)
        REFERENCES GroupChat(IDGroup)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

Create Table Messages
(
    IDMessage INT NOT NULL PRIMARY KEY,
    UserAccountSender VARCHAR(20),
    IDGroups INT,
    Messages VARCHAR(255),
    Constraint User_Sender FOREIGN KEY(UserAccountSender)
        REFERENCES Accounts(Username)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    Constraint User_Recievers FOREIGN KEY(IDGroups)
        REFERENCES GroupChat(IDGroup)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
