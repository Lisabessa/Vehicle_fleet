document.addEventListener("DOMContentLoaded", function (){
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function (event){
        event.preventDefault();

        const id = document.getElementById('idCar').value;
        const model = document.getElementById('model').value;
        const manufactureYear = document.getElementById('manufactureYear').value;
        const registrationDate = document.getElementById('registrationDate').value;
        const owner = document.getElementById('owner').value;

        const carData = {
            id: id,
            model: model,
            manufactureYear: manufactureYear,
            registrationDate: registrationDate,
            owner: owner
        };

        fetch('/api/cars/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(carData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Данные об автомобиле успешно обновлены.');
                    window.location.href = "/";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при изменении данных: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при изменении данных: ' + error);
            });
    });
});

