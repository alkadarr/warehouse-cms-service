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
		username VARCHAR(255) NULL,
		is_deleted bit NOT NULL,
		deleted_date DATETIME NULL
    )
END

GO

INSERT INTO [dbo].[User]
           ([created_by]
           ,[created_date]
           ,email
           ,username
           ,[password]
           ,is_deleted)
VALUES
('SYSTEM',GETDATE(),'superadmin@initial.com','super admin','$2a$10$Ah3EOC9BHsKkeqjGH6ECj.3Oh1yjVltKSSmW9pFcOo.GNoBVvZhp2','false')
-- password for this user is 12345678

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


GO

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='User_Role' and xtype='U')
CREATE TABLE [dbo].[User_Role](
	[user_id] BIGINT FOREIGN KEY REFERENCES [dbo].[User](id),
	[role_id] BIGINT FOREIGN KEY REFERENCES [dbo].[Role](id),
	PRIMARY KEY ([user_id],[role_id])
)

GO

INSERT INTO [dbo].[User_Role]
           ([user_id]
           ,[role_id])
VALUES
(1,1)

GO

CREATE TABLE [dbo].[Locations] (
    [id] INT PRIMARY KEY IDENTITY(1,1),
	[city] [varchar](255) NULL,
	[state] [varchar](255) NULL,
	[country] [varchar](255) NULL,
	[address] [varchar](255) NULL,
	[zip_code] [varchar](255) NULL,
	[created_date] [datetime] NOT NULL,
	[created_by] [varchar](50) NULL,
	[updated_date] [datetime2](6) NULL,
	[updated_by] [varchar](50) NULL
);

GO

CREATE TABLE [dbo].[Warehouses](
	[id] [int] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[name] [varchar](255) NOT NULL,
	[location_id] [int] NOT NULL FOREIGN KEY([location_id]) REFERENCES [dbo].[Locations] ([id]),
	[category] [varchar(50)] NOT NULL DEFAULT ('BRANCH')
	[capacity] [int] NULL,
	[is_active] [bit] NOT NULL DEFAULT ('true'),
	[created_date] [datetime] NOT NULL DEFAULT (GETDATE()),
	[created_by] [varchar](50) NULL,
	[updated_date] [datetime2](6) NULL,
	[updated_by] [varchar](50) NULL,
	[deleted_date] [datetime2](6) NULL,
	[is_deleted] [bit] NULL
);

GO




