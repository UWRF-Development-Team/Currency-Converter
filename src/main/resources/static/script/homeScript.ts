const euroValueText: HTMLElement = document.getElementById('eur-value');

async function getCurrencyValueFromServer(currencyCode: string): Promise<number> {
    const response = await fetch(`currency/value/${currencyCode}`);
    let jsonResponse = await response.json();
    return jsonResponse.value;
}
function updateEuroValue() {
    getCurrencyValueFromServer('EUR').then(value => {
        euroValueText.innerText = value.toString();
    });
}
updateEuroValue();