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
('SYSTEM',GETDATE(),'superadmin@initial.com','super admin','$2a$10$DUXvQ0ytJ1bmuUu6a0gQH.o9hHL5/56C35marvjEiFeaQNsK3iwsC','false')


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

CREATE TABLE Locations (
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

CREATE TABLE Warehouses (
    [id] INT PRIMARY KEY IDENTITY(1,1),
    [name] [varchar](255) NOT NULL,
	[location_id] [int] NOT NULL,
	[capacity] [int] NULL,
	[created_date] [datetime] NOT NULL,
	[created_by] [varchar](50) NULL,
	[updated_date] [datetime2](6) NULL,
	[updated_by] [varchar](50) NULL,
	[deleted_date] [datetime2](6) NULL,
	[is_deleted] [bit] NULL
);

GO

-- category
CREATE TABLE Category (
    [id] INT PRIMARY KEY IDENTITY(1,1),
    [name] VARCHAR(255) NOT NULL,
    [entity] VARCHAR(255) NULL
);

GO

-- product
CREATE TABLE Product (
    [id] BIGINT PRIMARY KEY IDENTITY(1,1),
    [name] VARCHAR(255) NOT NULL,
    [description] VARCHAR(1000),
    [price] DECIMAL(10, 2) NOT NULL,
    [gender] VARCHAR(50),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES Category(id)
);

GO

-- Product Stock
CREATE TABLE Product_Stock (
    [id] BIGINT PRIMARY KEY IDENTITY(1,1),
    product_id BIGINT,
    warehouse_id INT,
    stock_quantity INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES Product(id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouses(id)
);



