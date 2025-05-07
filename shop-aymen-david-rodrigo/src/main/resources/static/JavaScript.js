

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

function addToCart(productId, addUnits) {
    fetch(`${CONTEXT_PATH}api/v1/cart/add`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ productId: productId, addUnits: addUnits })
    })
        .then(async response => {
            const responseBody = await response.text();
            if (!response.ok) {
                throw new Error(`${responseBody}`);
            }
            return responseBody;
        })
        .then(data => {
            showToast("✅ Éxito", data, "bg-green-500");
        })
        .catch(error => {
            console.error("Error:", error);
            showToast("❌ Error", error.message, "bg-red-500");
        });
}


function showToast(title, message, bgColor) {
    const toastContainer = document.getElementById("toastContainer");

    const toast = document.createElement("div");
    toast.className = `flex items-center ${bgColor} text-white text-sm px-4 py-3 rounded-lg shadow-md transition-opacity opacity-100`;
    toast.innerHTML = `
        <strong class="mr-2">${title}:</strong> 
        <span>${message}</span>
        <button class="ml-auto text-white opacity-70 hover:opacity-100" onclick="this.parentElement.remove()">✖</button>
    `;

    toastContainer.appendChild(toast);

    setTimeout(() => {
        toast.classList.add("opacity-0");
        setTimeout(() => toast.remove(), 500);
    }, 4000);
}



document.addEventListener("click", function (event) {
    const quantityDisplay = document.getElementById("quantityDisplay");
    let quantity = parseInt(quantityDisplay.getAttribute("data-quantity"));

    if (event.target.matches(".quantity-btn")) {
        const action = event.target.getAttribute("data-action");
        if (action === "increase") {
            quantity++;
        } else if (action === "decrease" && quantity > 1) {
            quantity--;
        }
        quantityDisplay.textContent = quantity;
        quantityDisplay.setAttribute("data-quantity", quantity);
    }

    if (event.target.closest(".CartBtn")) {
        const button = event.target.closest(".CartBtn");
        const productId = button.getAttribute("data-product-id");
        if (productId) {
            addToCart(productId, quantity);
        } else {
            console.error("No se encontró el productId");
        }
    }
});