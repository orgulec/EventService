export function ChangeBorderButton(b){
    b.addEventListener('mouseover', a => b.classList.add("mouseover"));
    b.addEventListener('mouseout', a => b.classList.remove("mouseover"));
}
export function SetTime() {
    let time = new Date();
    const daysOfWeek = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    const currentDayOfWeek = daysOfWeek[time.getDay()];
    clock.innerHTML = "Today it's " + currentDayOfWeek + ", " + time.getDay() + "." + time.getMonth() + "." + time.getFullYear() + " " + time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds() + "<br>";
}