USE hotel;

select promotions.PromoID, promotions.PromoType, count(billpromos.PromoID) AS NumberOfUSes
from promotions
right join billpromos on billpromos.PromoID = promotions.PromoID
group by promotions.PromoID



