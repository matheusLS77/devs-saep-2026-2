const token = document
    .querySelector('meta[name="_csrf"]')
    .getAttribute('content');

const header = document
    .querySelector('meta[name="_csrf_header"]')
    .getAttribute('content');

document.querySelectorAll('.excluirMaterial')
    .forEach(function(button) {
        button.addEventListener('click', function() {
            if (!confirm('Confirma a exclusão?')) {
                return;
            }

            const linha = this.closest('tr');
            const id = this.dataset.id;

            fetch(`/materialexcluir/${id}`, {

                method: 'DELETE',

                headers: {
                    [header]: token
                }
            })
                .then(async response => {

                    if (response.ok) {
                        linha.remove();

                        alert(
                            'Material excluído com sucesso.'
                        );
                        return;
                    }

                    if (response.status === 403) {
                        alert(
                            'Apenas administradores podem excluir materiais.'
                        );
                        return;
                    }

                    const mensagem = await response.text();

                    alert(
                        mensagem ||
                        'Não foi possível excluir o material.'
                    );
                })
                .catch(error => {

                    console.error(error);

                    alert(
                        'Erro ao comunicar com o servidor.'
                    );
                });
        });
    });