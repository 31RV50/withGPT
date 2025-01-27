document.addEventListener("DOMContentLoaded", () => {
    const totalPixels = 1000000; // Total de píxeles disponibles
    let purchasedPixels = 0; // Contador de píxeles comprados

    const progressElement = document.getElementById("progress");
    const progressBarFill = document.getElementById("progress-bar-fill");
    const dynamicImage = document.getElementById("dynamicImage");

    // Actualiza el progreso basado en los píxeles comprados
    const updateProgress = () => {
        const percentage = ((purchasedPixels / totalPixels) * 100).toFixed(4); // Cambiado a 4 decimales
        progressElement.textContent = `${percentage}%`;
        progressBarFill.style.width = `${percentage}%`;

        // Actualiza el tamaño de la imagen dinámica
        const newHeight = 100 - percentage; // Tamaño reducido según el porcentaje
        dynamicImage.style.height = `${newHeight}%`;
    };

    // Función para manejar la compra de píxeles
    const handlePurchase = (pixels) => {
        if (purchasedPixels + pixels > totalPixels) {
            alert("No puedes comprar más píxeles de los disponibles.");
            return;
        }

        purchasedPixels += pixels;
        alert(`¡Compra realizada! Has comprado ${pixels} píxel(es).`);
        updateProgress();
    };

    // Asignar eventos a los botones de compra
    document.getElementById("buySinglePixel").addEventListener("click", () => handlePurchase(1));
    document.getElementById("buySquarePack").addEventListener("click", () => handlePurchase(9));
    document.getElementById("buyTetrisPack").addEventListener("click", () => handlePurchase(36));
    document.getElementById("buySquarePack10x10").addEventListener("click", () => handlePurchase(100));

    updateProgress();
});