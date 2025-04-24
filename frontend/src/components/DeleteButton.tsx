type DeleteButtonProps = {
    id: number;
    onDelete?: () => void;
};

export default function DeleteButton({ id, onDelete }: DeleteButtonProps) {
    const handleClick = async () => {
        await fetch(`http://localhost:8080/budget-items/${id}`, {
            method: 'DELETE',
        });

        onDelete?.();
    };

    return (
        <button onClick={handleClick}>
            Delete
        </button>
    );
}
