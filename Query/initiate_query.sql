IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'WAREHOUSE_CMS')
BEGIN
    CREATE DATABASE [WAREHOUSE_CMS]
END
GO

USE [WAREHOUSE_CMS]

GO

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='User' and xtype='U')
BEGIN
    CREATE TABLE [User] (
        id BIGINT PRIMARY KEY IDENTITY (1, 1),
		created_date DATETIME NOT NULL,
		created_by VARCHAR(50) NULL,
		updated_date DATETIME NULL,
		updated_by VARCHAR(50) NULL,
		email VARCHAR(255) NULL,
		[password] VARCHAR(MAX) NULL,
		username VARCHAR(255) NULL
    )
END

GO

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Role' and xtype='U')
BEGIN
    CREATE TABLE [Role] (
        id BIGINT PRIMARY KEY IDENTITY (1, 1),
		created_date DATETIME NOT NULL,
		created_by VARCHAR(50) NULL,
		updated_date DATETIME NULL,
		updated_by VARCHAR(50) NULL,
		[name] VARCHAR(20) NULL
	)
END

GO

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='User_Role' and xtype='U')
CREATE TABLE [dbo].[User_Role](
	[user_id] BIGINT FOREIGN KEY REFERENCES [dbo].[User](id),
	[role_id] BIGINT FOREIGN KEY REFERENCES [dbo].[Role](id),
	PRIMARY KEY ([user_id],[role_id])
)

GO

INSERT INTO [dbo].[Role]
           ([created_by]
           ,[created_date]
           ,[name])
VALUES
('SYSTEM',GETDATE(),'ROLE_SUPER_ADMIN'),
('SYSTEM',GETDATE(),'ROLE_ADMIN'),
('SYSTEM',GETDATE(),'ROLE_MANAGER'),
('SYSTEM',GETDATE(),'ROLE_CLERK'),
('SYSTEM',GETDATE(),'ROLE_AUDITOR'),
('SYSTEM',GETDATE(),'ROLE_WAREHOUSE_STAFF'),
('SYSTEM',GETDATE(),'ROLE_SEARCH_EXPORT')



