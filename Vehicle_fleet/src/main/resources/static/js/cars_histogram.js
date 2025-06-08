document.addEventListener('DOMContentLoaded', function () {
    const ctx = document.getElementById('carsHistogram').getContext('2d');

    const labels = window.chartLabels || [];
    const dataCounts = window.chartDataCounts || [];

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Количество машин',
                data: dataCounts,
                backgroundColor: '#bc8bec',
                borderColor: '#9842ed',
                borderWidth: 1,
                borderRadius: 4
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                x: {
                    title: {
                        display: true,
                        text: 'Дата регистрации'
                    },
                    ticks: {
                        maxRotation: 90,
                        minRotation: 45
                    }
                },
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Количество машин'
                    },
                    ticks: {
                        stepSize: 1
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    enabled: true
                }
            }
        }
    });
});
