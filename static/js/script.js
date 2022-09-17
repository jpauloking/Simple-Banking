const accountsSelectField= document.getElementById('accounts');
accountsSelectField.addEventListener('select', e => {
	fetch('/accounts')
	.then(res => {
		console.log(res.body);
	})
	.catch(err => {
		console.error(err);
	})
});
