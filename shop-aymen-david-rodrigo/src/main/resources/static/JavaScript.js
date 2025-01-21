document.getElementById('query-input').addEventListener('keyup', async function (e) {
    const query = e.target.value.trim();
    const dropdown = document.getElementById('dropdown');
    console.log("PRESIONANDO", query); // Debug: ver el valor de la consulta

    if (query.length > 2) {
        console.log("Iniciando la búsqueda..."); // Debug: indica que se está enviando la solicitud

        const response = await fetch(`/search?query=${encodeURIComponent(query)}`);
        if (!response.ok) {
            console.error("Error al realizar la solicitud", response.status); // Debug: error en la solicitud
            return;
        }

        const productos = await response.json();
        console.log('Productos encontrados:', productos); // Verifica si ambos productos están aquí

        dropdown.innerHTML = '';

        if (productos.length > 0) {
            productos.forEach(producto => {
                const item = document.createElement('div');
                item.textContent = producto.name;
                item.style.padding = '10px';
                item.style.cursor = 'pointer';
                item.onclick = () => {
                    console.log(`Navegando a /categories/product/${producto.id}`); // Debug: muestra la acción al hacer clic
                    window.location.href = `/categories/product/${producto.id}`;
                };
                dropdown.appendChild(item);
            });

            dropdown.style.display = 'block';
        } else {
            console.log("No se encontraron productos."); // Debug: no se encontraron productos
            dropdown.style.display = 'none';
        }
    } else {
        console.log("Consulta demasiado corta para buscar."); // Debug: no se realiza búsqueda si la consulta es demasiado corta
        dropdown.style.display = 'none';
    }
});

// Cerrar el dropdown si se hace clic fuera
document.addEventListener('click', function (event) {
    const input = document.getElementById('query-input');
    const dropdown = document.getElementById('dropdown');
    if (!input.contains(event.target) && !dropdown.contains(event.target)) {
        console.log("Cerrando el dropdown"); // Debug: indica que se cierra el dropdown
        dropdown.style.display = 'none';
    }
});
