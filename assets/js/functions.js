// Crear partículas flotantes
function createParticles() {
    const particles = document.getElementById('particles');
    for (let i = 0; i < 20; i++) {
        const particle = document.createElement('div');
        particle.className = 'particle';
        particle.style.left = Math.random() * 100 + '%';
        particle.style.top = Math.random() * 100 + '%';
        particle.style.width = Math.random() * 4 + 2 + 'px';
        particle.style.height = particle.style.width;
        particle.style.animationDelay = Math.random() * 6 + 's';
        particles.appendChild(particle);
    }
}

// Efecto de inmersión con scroll y control de legibilidad
function updateOceanDepth() {
    const scrolled = window.pageYOffset;
    const rate = scrolled * -0.5;
    const sections = document.querySelectorAll('.section');
    const sunRays = document.getElementById('sunRays');
    const windowHeight = window.innerHeight;

    // Desvanecer rayos de sol
    const opacity = Math.max(0, 1 - (scrolled / 800));
    sunRays.style.opacity = opacity;

    // Controlar legibilidad de secciones
    sections.forEach((section, index) => {
        const sectionTop = section.offsetTop;
        const sectionHeight = section.offsetHeight;
        const sectionCenter = sectionTop + (sectionHeight / 2);
        const viewportCenter = scrolled + (windowHeight / 2);

        // Calcular si la sección está en el viewport activo (centro de la pantalla)
        const distanceFromCenter = Math.abs(sectionCenter - viewportCenter);
        const maxDistance = windowHeight * 0.8; // Zona de activación

        if (distanceFromCenter < maxDistance) {
            // Sección activa - texto completamente legible
            section.classList.remove('fading');
            const brightness = 1;
            section.style.filter = `brightness(${brightness})`;
        } else {
            // Sección inactiva - difuminar
            section.classList.add('fading');
            const brightness = 0.3;
            section.style.filter = `brightness(${brightness})`;
        }
    });

    // Mover partículas
    const particles = document.querySelectorAll('.particle');
    particles.forEach((particle, index) => {
        const speed = 1 + index * 0.1;
        particle.style.transform = `translateY(${rate * speed}px)`;
    });
}

// Smooth scrolling para navegación
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

// Inicializar
window.addEventListener('load', () => {
    createParticles();
    updateOceanDepth();
});

window.addEventListener('scroll', updateOceanDepth);