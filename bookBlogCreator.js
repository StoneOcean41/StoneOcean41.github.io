document.addEventListener("DOMContentLoaded", function() {
    fetch("books.json")
        .then(response => response.json())
        .then(data => {
            const bookJournal = document.getElementById("book-journal");

            data.forEach(book => {
                const bookDiv = document.createElement("div");
                bookDiv.classList.add("book");

                // Create HTML structure for each book entry
                bookDiv.innerHTML = `
                    <div class="left-book">
                        <img src="${book.cover}" class="book-cover" id = "${book.id}">
                        <div class="details">
                            <div class="title">
                                <p>${book.title}</p>
                            </div>
                            <div class="author">
                                <p>by ${book.author}</p>
                            </div>
                            <div class="star-rating">
                                ${getStarRating(book.rating)}
                            </div>
                        </div>
                    </div>
                    <div class="right-book">
                        <p>${book.review}</p>
                    </div>
                `;

                // Append book div to the book journal container
                bookJournal.appendChild(bookDiv);
            });
        })
        .catch(error => console.error("Error fetching book data:", error));

    // Function to generate star rating HTML
    function getStarRating(rating) {
        const fullStars = Math.floor(rating);
        const halfStar = rating % 1 !== 0;
        const emptyStar = 5 - (fullStars + halfStar);

        let starsHTML = '';
        for (let i = 0; i < fullStars; i++) {
            starsHTML += '<i class="fa-solid fa-star"></i>';
        }
        if (halfStar) {
            starsHTML += '<i class="fa-solid fa-star-half-stroke"></i>';
        }
        for (let i = 0; i < emptyStar; i++) {
            starsHTML += '<i class="fa-regular fa-star"></i>';
        }
        return starsHTML;
    }
});
