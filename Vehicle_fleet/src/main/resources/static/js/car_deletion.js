function deleteEntity(button) {
    const id = button.getAttribute('idEntity');

    if (confirm('Вы уверены, что хотите удалить этот автомобиль?')) {
        fetch('/api/cars/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('Автомобиль успешно удален.');
                    window.location.reload();
                } else {
                    response.text().then(message => {
                        alert('Ошибка при удалении автомобиля: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при удалении автомобиля: ' + error);
            });
    }
}
