document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // TODO: get data from inputs and show them in summary
        }

    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }


    //inserting elements to maintain the correct CSS styles with spring mvc form tags
    const checkboxInput = $(".auxiliary");
    const insertElement = $("<span class=\"checkbox\"></span>");
    insertElement.insertAfter(checkboxInput);


    /**
     * Acquiring entered values for summary
     */
    let checkedCategories = '';
    let checkedRadio = '';
    let quantity;
    //if last next button is clicked
    $('.btn.next-step:last').on("click", function (event) {

        /**
         * Handling of checkboxes with categories
         */

        //Getting checked checkboxes elements
        let checkedCheckboxes = $("input:checked.auxiliary").parent();//dla radio bez tego .auxiliary

        //Getting categories names
        checkedCheckboxes.each(function () {
            let category = $(this).children().last().text();
            checkedCategories += category;
            checkedCategories = checkedCategories.substring(0, checkedCategories.length - 1);
            checkedCategories += ';';

        });
        checkedCategories = checkedCategories.substring(0, checkedCategories.length - 1);

        //inserting span with categories names to jsp file
        $("<span class=\"summary--text\"></span>").text(checkedCategories).insertAfter($("li").find("span:contains('O kategoriach')"));

        /**
         * Handling of radio buttons with institutions
         */
        checkedRadio = $("input:checked:not(.auxiliary)").parent().children().last().find('div.title').text();
        $("<span class=\"summary--text\"></span>").text(checkedRadio).insertAfter($("li").find("span:contains('Dla')"));

        /**
         * Handling of input with quantity
         */
        quantity = $("#quantity").val();
        $("<span class=\"summary--text\"></span>").text(quantity).insertAfter($("li").find("span:contains('Worków')"));

        /**
         * Handling of other inputs
         */
        $("div[data-step='5']").find("h4:contains('Adres')").next().children().first().text($("#street").val());
        $("div[data-step='5']").find("h4:contains('Adres')").next().children().first().next().text($("#city").val());
        $("div[data-step='5']").find("h4:contains('Adres')").next().children().last().prev().text($("#zipCode").val());
        $("div[data-step='5']").find("h4:contains('Adres')").next().children().last().text($("#telephoneNumber").val());

        $("div[data-step='5']").find("h4:contains('Termin')").next().children().first().text($("#pickUpDate").val());
        $("div[data-step='5']").find("h4:contains('Termin')").next().children().first().next().text($("#pickUpTime").val());
        $("div[data-step='5']").find("h4:contains('Termin')").next().children().last().text($("#pickUpComment").val());

        //preventing from default click
        event.preventDefault();
    });

    //if last previous button is clicked
    $('.btn.prev-step:last').on("click", function (event) {

        $("span.icon-bag").parent().children().first().next().next().remove();
        quantity = '';

        $("span.icon-bag").parent().children().last().remove();
        checkedCategories = '';

        $("span.icon-hand").parent().children().last().remove();
        checkedRadio = '';

        event.preventDefault();
    });
    
});
