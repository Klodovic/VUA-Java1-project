create database CineStarMovie
go 

use CineStarMovie
go

/*Database tables*/
create table Roles(
	IDRole int primary key identity,
	[Role] nvarchar(25) not null
)

create table Users(
	IDUser int primary key identity,
	Username nvarchar(25) not null,
	[Password] nvarchar(600) not null,
	RoleID int foreign key references Roles(IDRole)
)

create table Movie(
	IDMovie int primary key identity,
	Title nvarchar(100) not null,
	PubDate nvarchar(25) not null,
	[Description] nvarchar(MAX) not null,
	OriginalTitle nvarchar(100) not null,	
	Duration nvarchar(10),
	PosterPath nvarchar(300) not null,
	Link nvarchar(300) not null,
	Expected nvarchar(25) not null
)

create table Actor(
	IDActor int primary key identity,
	ActorName nvarchar(50)
)

CREATE TABLE MovieCast
(
	IDMovieCast int not null primary key identity,
	MovieID int not null foreign key references Movie(IDMovie),
	ActorID int null foreign key references Actor(IDActor)
)

create table Director(
	IDDirector int primary key identity,
	DirectorName nvarchar(50) null
)

CREATE TABLE MovieDirection
(
	IDMovieDirection int not null primary key identity,
	MovieID int not null foreign key references Movie(IDMovie),
	DirectorID int null foreign key references Director(IDDirector)
)

create table Genre(
	IDGenre int primary key identity,
	GenreName nvarchar(50)
)

CREATE TABLE MovieGenre
(
	IDMovieGenre int not null primary key identity,
	MovieID int not null foreign key references Movie(IDMovie),
	GenreID int null foreign key references Genre(IDGenre)
)


/*SETUP INSERT*/
insert into Roles values ('Administrator')
insert into Roles values ('User')
insert into Users values ('admin', 'admin77', 1)
go




/***************************MOVIE PROCEDURES**************************/

/*Register - check if user exists*/
create proc checkIfUserExists
	@Username nvarchar(25),
	@exists int output
as
begin
	declare @count int
	select @count = COUNT(*) from Users where Username = @Username
	if @count = 1
		begin
			set @exists = 1
		end
	else
		begin
			set @exists = 0
		end
end
go

/*Register -  create new user*/
create proc createUser
	@UserName nvarchar(25),
	@Password nvarchar(600),
	@RoleID int,
	@IdUser int output
as
begin 
	insert into Users values(@UserName, @Password, @RoleID)
	set @IdUser = SCOPE_IDENTITY()
end
go

/*Register -  select user*/
create proc selectUser
	@UserName nvarchar(25)
as
begin 
	select * from Users where Username = @UserName
end
go


/*Create movies from RSS (Admin panel - btnUpload*/
create proc createMovie
	@Title nvarchar(100),
	@PubDate nvarchar(25),
	@Description nvarchar(MAX),
	@OriginalTitle nvarchar(100),	
	@Duration nvarchar(10),
	@PosterPath nvarchar(300),
	@Link nvarchar(300),
	@Expected nvarchar(25),
	@IdMovie int output
as
begin 
	insert into Movie values(@Title, @PubDate, @Description, @OriginalTitle, @Duration, @PosterPath, @Link, @Expected)
	set @IdMovie = SCOPE_IDENTITY()
end
go

/*Check if Director exists*/
create proc selectDirectorName
	@DirectorName nvarchar(50)
as
begin
	select * from Director where DirectorName = @DirectorName
end
go

/*Check if Actor exists*/
create proc selectActorName
	@ActorName nvarchar(50)
as
begin
	select * from Actor where ActorName = @ActorName
end
go

/*Check if Genre exists*/
create proc selectGenreName
	@GenreName nvarchar(50)
as
begin
	select * from Genre where GenreName = @GenreName
end
go

/*Create directors from RSS (Admin panel - btnUpload*/
create proc createDirectors
	@MovieID int,
	@DirectorName nvarchar(50),
	@IdDirector int output
as
begin 
	insert into Director values (@DirectorName)
	set @IdDirector = SCOPE_IDENTITY()
	insert into MovieDirection values (@MovieID, @IdDirector)
end
go

/*Create actors from RSS (Admin panel - btnUpload*/
create proc createActors
	@MovieID int,
	@ActorName nvarchar(50),
	@IdActor int output
as
begin 
	insert into Actor values (@ActorName)
	set @IdActor = SCOPE_IDENTITY()
	insert into MovieCast values (@MovieID, @IdActor)
end
go

/*Create genres from RSS (Admin panel - btnUpload*/
create proc createGenres
	@MovieID int,
	@GenreName nvarchar(50),
	@IdGenre int output
as
begin 
	insert into Genre values (@GenreName)
	set @IdGenre = SCOPE_IDENTITY()
	insert into MovieGenre values (@MovieID, @IdGenre)
end
go

/*Populate movieDirection*/
create proc populateMovieDirection
	@MovieID int,
	@IDDirector int
as
begin
	insert into MovieDirection values (@MovieID, @IdDirector) 
	select * from MovieDirection
	where MovieID = @MovieID
end
go

/*Populate movieCast*/
create proc populateMovieCast
	@MovieID int,
	@IDActor int
as
begin
	insert into MovieCast values (@MovieID, @IDActor) 
	select * from MovieCast
	where MovieID = @MovieID
end
go

/*Populate movieGenre*/
create proc populateMovieGenre
	@MovieID int,
	@IDGenre int
as
begin
	insert into MovieGenre values (@MovieID, @IDGenre) 
	select * from MovieGenre
	where MovieID = @MovieID
end
go

/*Load all movies from database (Admin panel - on panel loading)*/
create proc selectMovies
as
begin
	select * from Movie
end
go

/*Deleting all movies from database (Admin panel - btnDelete)*/
create proc eraseAllMoviesFromDatabase
as
begin
	delete from MovieCast
	delete from MovieDirection
	delete from MovieGenre
	delete from Genre
	delete from Actor
	delete from Director
	delete from Movie
end
go

/*User panel - movie details*/
create proc selectMovie
	@IDMovie int
as
begin
	select * from Movie where IDMovie = @IDMovie
end
go

/*New Movie - select all Actors*/
create proc selectAllActors
as
begin
	select * from Actor
end
go

/*New Movie - select all Directors*/
create proc selectAllDirectors
as
begin
	select * from Director
end
go

/*New Movie - select all Genres*/
create proc selectAllGernes
as
begin
	select * from Genre
end
go

/*New Movie - create new actor for new movie*/
create proc createNewActor
	@ActorName nvarchar(50)
as
begin
	insert into Actor values (@ActorName) 
end
go

/*New Movie - create new director for new movie*/
create proc createNewDirector
	@DirectorName nvarchar(50)
as
begin
	insert into Director values (@DirectorName) 
end
go

/*New Movie - create new genre for new movie*/
create proc createNewGenre
	@DGenreName nvarchar(50)
as
begin
	insert into Genre values (@DGenreName) 
end
go

/*Edit - Update Movie - get actors for selected movie*/
create proc selectActors
	@IDMovie int
as
begin
	select a.IDActor, a.ActorName from Actor a
	join MovieCast mc on mc.ActorID=a.IDActor
	join Movie m on m.IDMovie=mc.MovieID
	where IDMovie = @IDMovie
end
go

/*Edit - Update Movie - get directors for selected movie*/
create proc selectDirectors
	@IDMovie int
as
begin
	select IDDirector, DirectorName from Director 
	join MovieDirection on DirectorID=IDDirector
	join Movie on IDMovie=MovieID
	where IDMovie = @IDMovie
end
go

/*Edit - Update Movie - get genres for selected movie*/
create proc selectGernes
	@IDMovie int
as
begin
	select IDGenre, GenreName from Genre 
	join MovieGenre on GenreID=IDGenre
	join Movie on IDMovie=MovieID
	where IDMovie = @IDMovie
end

/*Edit - update movie - button update*/
create proc updateMovie
	@Title nvarchar(100),
	@PubDate nvarchar(25),
	@Description nvarchar(MAX),
	@OriginalTitle nvarchar(100),	
	@Duration nvarchar(10),
	@PosterPath nvarchar(300),
	@Link nvarchar(300),
	@Expected nvarchar(25),
	@IDMovie int
as
begin 
	UPDATE Movie SET 
	Title = @title, PubDate = @PubDate, [Description] = @Description, OriginalTitle = @OriginalTitle, Duration = @Duration, 
	PosterPath = @PosterPath, Link = @Link, Expected = @Expected
	where IDMovie = @IDMovie
end
go

/*Edit-update movie  -  update movies actors - button update*/
create proc uploadMovieCast
	@MovieID int,
	@IDActor int
as
begin
	UPDATE MovieCast SET 
	ActorID = @IDActor
	where MovieID = @MovieID
end
go

/*Edit-update movie  -  update movies directors - button update*/
create proc uploadMovieDirection
	@MovieID int,
	@IDDirector int
as
begin
	UPDATE MovieDirection SET 
	DirectorID = @IDDirector
	where MovieID = @MovieID
end
go

/*Edit-update movie  -  update movies genres - button update*/
create proc uploadMovieGenre
	@MovieID int,
	@IDGenre int
as
begin
	UPDATE MovieGenre SET 
	GenreID = @IDGenre
	where MovieID = @MovieID
end
go

/*Edit-update movie  -  remove genres when edit - button update*/
create proc removeActorsFromMovieCast
	@MovieID int
as
begin
	delete from MovieCast
	where MovieID = @MovieID
end
go

/*Edit-update movie  -  remove genres when edit - button update*/
create proc removeDirectorsFromMovieDirection
	@MovieID int
as
begin
	delete from MovieDirection
	where MovieID = @MovieID
end
go

/*Edit-update movie  -  remove genres when edit - button update*/
create proc removeGenresFromMovieGenre
	@MovieID int
as
begin
	delete from MovieGenre
	where MovieID = @MovieID
end
go

/*Edit-update movie  -  delete selected movie - btnDelete*/
create proc deleteSelectedMovie
	@MovieID int
as
begin
	delete from MovieGenre where MovieID = @MovieID
	delete from MovieCast where MovieID = @MovieID
	delete from MovieDirection where MovieID = @MovieID
	delete from Movie where IDMovie = @MovieID
end
go

/*Edit Panel - update actor*/
create proc updateActor
	@IdActor int,
	@ActorName nvarchar(50)
as
begin
	UPDATE Actor SET 
	ActorName = @ActorName
	where IdActor = @IdActor
end
go

/*Edit Panel - update director*/
create proc updateDirector
	@IdDirector int,
	@DirectorName nvarchar(50)
as
begin
	UPDATE Director SET 
	DirectorName = @DirectorName
	where IdDirector = @IdDirector
end
go

/*Edit Panel - update actor*/
create proc updateGenre
	@IdGenre int,
	@GenreName nvarchar(50)
as
begin
	UPDATE Genre SET 
	GenreName = @GenreName
	where IdGenre = @IdGenre
end
go

/*Edit Panel - delete actor - check if actor is related with a movie*/
create proc checkIfActorRelatesToAMovie
	@IdActor int,
	@returnCode int output
as
begin
	declare @count int
	select @count = COUNT(ActorID) from MovieCast where ActorID = @IdActor
	if @count = 0
		begin
			set @returnCode = 0
		end
	else
		begin
			set @returnCode = -1
		end
end
go

/*Edit Panel - delete actor - check if actor is related with a movie*/
create proc checkIfDirectorRelatesToAMovie
	@IdDirector int,
	@returnCode int output
as
begin
	declare @count int
	select @count = COUNT(DirectorID) from MovieDirection where DirectorID = @IdDirector
	if @count = 0
		begin
			set @returnCode = 0
		end
	else
		begin
			set @returnCode = -1
		end
end
go

/*Edit Panel - delete actor - check if actor is related with a movie*/
create proc checkIfGenreRelatesToAMovie
	@IdGenre int,
	@returnCode int output
as
begin
	declare @count int
	select @count = COUNT(GenreID) from MovieGenre where GenreID = @IdGenre
	if @count = 0
		begin
			set @returnCode = 0
		end
	else
		begin
			set @returnCode = -1
		end
end
go

/*Edit Panel - delete actor*/
create proc deleteActor
	@IdActor int
as
begin
	delete from Actor where IdActor = @IdActor
end
go

/*Edit Panel - delete actor*/
create proc deleteDirector
	@IdDirector int
as
begin
	delete from Director where IdDirector = @IdDirector
end
go

/*Edit Panel - delete actor*/
create proc deleteGenre
	@IdGenre int
as
begin
	delete from Genre where IdGenre = @IdGenre
end
go

select * from Director
select * from Movie m
select * from MovieDirection
select * from Actor
select * from MovieGenre
select * from Genre
select * from MovieCast m





