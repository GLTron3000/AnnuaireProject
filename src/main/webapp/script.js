// Init responsive navbar
document.addEventListener('DOMContentLoaded', () => {
	const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0)
    if ($navbarBurgers.length > 0) {
    	$navbarBurgers.forEach( e => {
    		e.addEventListener('click', () => {
		        const target = e.dataset.target
		        const $target = document.getElementById(target)
		
		        e.classList.toggle('is-active')
		        $target.classList.toggle('is-active')
	        })
    	})
    }
	
	background = document.getElementById('mainPage')

	if(background != null) {
		changeBackground()
		setInterval(changeBackground, 5000)
	}
})

// select search index
function selectCallback() {
	const selection = document.getElementById('searchSelect').value
	const form = document.getElementById('searchForm')
	
	switch(selection) {
		case 'group': form.action = '/groups/find'; break
		case 'person': form.action = '/profiles/find'; break
	}
	
}


// blagounette
var background
const colorArray = ['4eb287', '42c621', 'c6c421', 'c68221', 'c62421', '21c6be', ' 2124c6', ' b021c6']
var currentColor = '000000'

function changeBackground(){
	var color = currentColor
	while(currentColor == color) color = colorArray[Math.floor(Math.random() * colorArray.length)]
	currentColor = color
	
	background.style.backgroundColor = '#' + color
}
