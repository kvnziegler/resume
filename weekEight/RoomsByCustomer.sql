USE hotel;

select name, RoomID
from roomres
right join reservation on reservation.reservationID = roomres.ResID
right join people on people.PeopleID = reservation.reservationID
where name = 'Cherey Fishleigh';
