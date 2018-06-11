-- AreasProfesionales
USE [ojs]
GO

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

INSERT INTO [dbo].[AreasProfesionales] ([Area]) VALUES ('Licenciado en sociologia')
GO
INSERT INTO [dbo].[AreasProfesionales] ([Area]) VALUES ('Licenciado en antropologia social')
GO
INSERT INTO [dbo].[AreasProfesionales] ([Area]) VALUES ('Licenciado en ciencias sociales')
GO
INSERT INTO [dbo].[AreasProfesionales] ([Area]) VALUES ('Licenciado en sociologia rural')
GO
INSERT INTO [dbo].[AreasProfesionales] ([Area]) VALUES ('Licenciado en antropologia social de la educacion')
GO
INSERT INTO [dbo].[AreasProfesionales] ([Area]) VALUES ('Licenciado en sociologia del trabajo')
GO
INSERT INTO [dbo].[AreasProfesionales] ([Area]) VALUES ('Licenciado en sociologia del trabajo')
GO

-- Estados
USE [ojs]
GO

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

INSERT INTO [dbo].[Estados] ([Estado], [Orden]) VALUES ('Devuelto', 1)
GO
INSERT INTO [dbo].[Estados] ([Estado], [Orden]) VALUES ('Registrado', 2)
GO
INSERT INTO [dbo].[Estados] ([Estado], [Orden]) VALUES ('Aprobado Evaluador', 3)
GO
INSERT INTO [dbo].[Estados] ([Estado], [Orden]) VALUES ('Aprobado Comité', 4)
GO
INSERT INTO [dbo].[Estados] ([Estado], [Orden]) VALUES ('Rechazado', 5)
GO

-- Roles
USE [ojs]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Roles](
	[idRol] [smallint] IDENTITY(1,1) NOT NULL,
	[Rol] [varchar](15) NOT NULL,
	[Tipo] [varchar](7) NOT NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[idRol] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

INSERT INTO [dbo].[Roles] ([Rol], [Tipo]) VALUES ('ROOT', 'Privado')
GO
INSERT INTO [dbo].[Roles] ([Rol], [Tipo]) VALUES ('ADMINISTRADOR', 'Privado')
GO
INSERT INTO [dbo].[Roles] ([Rol], [Tipo]) VALUES ('VALIDADOR', 'Privado')
GO
INSERT INTO [dbo].[Roles] ([Rol], [Tipo]) VALUES ('COMITE', 'Privado')
GO
INSERT INTO [dbo].[Roles] ([Rol], [Tipo]) VALUES ('EVALUADOR', 'Público')
GO
INSERT INTO [dbo].[Roles] ([Rol], [Tipo]) VALUES ('ESCRITOR', 'Público')
GO

--Usuarios
USE [ojs]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Usuarios](
	[idUsuario] [int] IDENTITY(1,1) NOT NULL,
	[idMD5] [varchar](32) NULL,
	[idRol] [smallint] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Correo] [varchar](50) NOT NULL,
	[Contrasena] [varchar](50) NULL,
	[Estado] [bit] NOT NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[idUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_Usuarios] UNIQUE NONCLUSTERED 
(
	[Correo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Usuarios] ADD  CONSTRAINT [DF_Usuarios_Estado]  DEFAULT ((1)) FOR [Estado]
GO

ALTER TABLE [dbo].[Usuarios]  WITH CHECK ADD  CONSTRAINT [FK_Usuarios_Roles] FOREIGN KEY([idRol])
REFERENCES [dbo].[Roles] ([idRol])
GO

ALTER TABLE [dbo].[Usuarios] CHECK CONSTRAINT [FK_Usuarios_Roles]
GO

INSERT INTO [dbo].[Usuarios] ([idRol], [Nombre], [Correo],[Contrasena])
     VALUES (1, 'Root', 'root@ojs.co', '40bd001563085fc35165329ea1ff5c5ecbdbbeef')
GO
INSERT INTO [dbo].[Usuarios] ([idRol], [Nombre], [Correo],[Contrasena])
     VALUES (2, 'Administrador', 'admin@ojs.co', '40bd001563085fc35165329ea1ff5c5ecbdbbeef')
GO
INSERT INTO [dbo].[Usuarios] ([idRol], [Nombre], [Correo],[Contrasena])
     VALUES (3, 'Validador', 'valid@ojs.co', '40bd001563085fc35165329ea1ff5c5ecbdbbeef')
GO
INSERT INTO [dbo].[Usuarios] ([idRol], [Nombre], [Correo],[Contrasena])
     VALUES (4, 'Comité', 'comite@ojs.co', '40bd001563085fc35165329ea1ff5c5ecbdbbeef')
GO

--UsuariosRegistrados
USE [ojs]
GO

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
