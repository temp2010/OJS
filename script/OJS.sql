-- AreasProfesionales
USE [ojs]
GO

/****** Object:  Table [dbo].[AreasProfesionales]    Script Date: 05/06/2018 05:26:27 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[AreasProfesionales](
	[idArea] [smallint] IDENTITY(1,1) NOT NULL,
	[Area] [varchar](50) NOT NULL,
 CONSTRAINT [PK_AreasProfesionales] PRIMARY KEY CLUSTERED 
(
	[idArea] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

-- Estados
USE [ojs]
GO

/****** Object:  Table [dbo].[Estados]    Script Date: 05/06/2018 05:26:53 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Estados](
	[idEstado] [smallint] IDENTITY(1,1) NOT NULL,
	[Estado] [varchar](50) NOT NULL,
	[Orden] [smallint] NULL,
 CONSTRAINT [PK_Estados] PRIMARY KEY CLUSTERED 
(
	[idEstado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

-- Roles
USE [ojs]
GO

/****** Object:  Table [dbo].[Roles]    Script Date: 05/06/2018 05:27:08 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Roles](
	[idRol] [smallint] IDENTITY(1,1) NOT NULL,
	[Rol] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[idRol] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

--Usuarios
USE [ojs]
GO

/****** Object:  Table [dbo].[Usuarios]    Script Date: 05/06/2018 05:27:40 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Usuarios](
	[idUsuario] [int] IDENTITY(1,1) NOT NULL,
	[idRol] [smallint] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Correo] [varchar](50) NOT NULL,
	[Contrasena] [varchar](50),
	[Estado] [bit] NOT NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[idUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Usuarios]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_Roles] FOREIGN KEY([idRol])
REFERENCES [dbo].[Roles] ([idRol])
GO

ALTER TABLE [dbo].[Usuarios] CHECK CONSTRAINT [FK_Usuarios_Roles]
GO

--UsuariosRegistrados
USE [ojs]
GO

/****** Object:  Table [dbo].[UsuariosRegistrados]    Script Date: 05/06/2018 05:28:10 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[UsuariosRegistrados](
	[idUsuario] [int] NOT NULL,
	[idEstado] [smallint] NOT NULL,
	[idArea] [smallint] NOT NULL,
	[FechaRegistro] [date] NOT NULL,
	[Perfil] [text] NULL,
	[Tematicas] [text] NULL,
	[Observacion] [text] NULL,
 CONSTRAINT [PK_UsuariosRegistrados] PRIMARY KEY CLUSTERED 
(
	[idUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[UsuariosRegistrados] ADD  CONSTRAINT [DF_UsuariosRegistrados_FechaRegistro]  DEFAULT (getdate()) FOR [FechaRegistro]
GO

ALTER TABLE [dbo].[UsuariosRegistrados]  WITH CHECK ADD  CONSTRAINT [FK_UsuariosRegistrados_Estados] FOREIGN KEY([idEstado])
REFERENCES [dbo].[Estados] ([idEstado])
GO

ALTER TABLE [dbo].[UsuariosRegistrados] CHECK CONSTRAINT [FK_UsuariosRegistrados_Estados]
GO

ALTER TABLE [dbo].[UsuariosRegistrados]  WITH CHECK ADD  CONSTRAINT [FK_UsuariosRegistrados_Areas] FOREIGN KEY([idArea])
REFERENCES [dbo].[AreasProfesionales] ([idArea])
GO

ALTER TABLE [dbo].[UsuariosRegistrados] CHECK CONSTRAINT [FK_UsuariosRegistrados_Areas]
GO

ALTER TABLE [dbo].[UsuariosRegistrados]  WITH CHECK ADD  CONSTRAINT [FK_UsuariosRegistrados_Usuarios] FOREIGN KEY([idUsuario])
REFERENCES [dbo].[Usuarios] ([idUsuario])
GO

ALTER TABLE [dbo].[UsuariosRegistrados] CHECK CONSTRAINT [FK_UsuariosRegistrados_Usuarios]
GO

-- Soportes
USE [ojs]
GO

/****** Object:  Table [dbo].[Soportes]    Script Date: 05/06/2018 05:27:21 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Soportes](
	[idSoporte] [int] IDENTITY(1,1) NOT NULL,
	[idUsuario] [int] NOT NULL,
	[Soporte] [varchar](128) NOT NULL,
 CONSTRAINT [PK_Soportes] PRIMARY KEY CLUSTERED 
(
	[idSoporte] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Soportes]  WITH CHECK ADD  CONSTRAINT [FK_Soportes_UsuariosRegistrados] FOREIGN KEY([idUsuario])
REFERENCES [dbo].[UsuariosRegistrados] ([idUsuario])
GO

ALTER TABLE [dbo].[Soportes] CHECK CONSTRAINT [FK_Soportes_UsuariosRegistrados]
GO

